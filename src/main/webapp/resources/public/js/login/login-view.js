define( function ( require ) {

	'use strict';

	var Backbone = require( 'backbone' );
	var _ = require( 'underscore' );
	var $ = require( 'jquery' );

	var bootbox = require( 'bootbox' );

	var template = _.template( require( 'text!./templates/login-template.html' ) );

	var Translator = require( 'translator' );
	var t = new Translator( {
		loginLabel: 'Login'
		, passwordLabel: 'Password or credit card number'
		, loginFailedLabel: 'Login failed'
		, loginFailedMessage: "Login failed or there are not enough money on the credit card ( depends what you've filled in the field )"
		, rememberMeLabel: "Remember me ( and my card number )"
	} );

	return Backbone.View.extend( {

		events: {
			'click .js-login-button': '__onLoginClick'
		},

		initialize: function ( options ) {
			this.render();
		},

		render: function () {
			this.$el.html( template( { t : t } ) );
		},

		__onLoginClick: function() {
			this.__authenticate( this.$( '#login-form' ).serializeArray() );
		},

		__authenticate: function( options ) {

			$.ajax( {
				method: 'POST',
				url: '/authenticate',
				data: options,
				headers: {
					"Content-Type": "application/x-www-form-urlencoded",
					"X-Login-Ajax-call": 'true'
				},
				success: function ( response ) {

					if ( response === 'ok' ) {
						window.location.replace( '/scheduler/' );
						return;
					}

					bootbox.dialog( {
						title: t.loginFailedLabel
						, message: t.loginFailedMessage
					} );
				}
			} )
		}
	} );
} );