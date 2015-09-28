define( function ( require ) {

	'use strict';

	var Backbone = require( 'backbone' );
	var _ = require( 'underscore' );
	var $ = require( 'jquery' );

	var template = _.template( require( 'text!./templates/login-template.html' ) );

	var Translator = require( 'translator' );
	var translator = new Translator( {
		loginLabel: 'Login'
		, passwordLabel: 'Password or credit card number'
	} );

	return Backbone.View.extend( {

		events: {
			'click .js-login-button': '__onLoginClick'
		},

		initialize: function ( options ) {
			this.render();
		},

		render: function () {
			this.$el.html( template( { t : translator} ) );
		},

		___onLoginClick: function() {

		}
	} );
} );