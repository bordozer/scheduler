define( function ( require ) {

	'use strict';

	var Backbone = require( 'backbone' );
	var _ = require( 'underscore' );
	var $ = require( 'jquery' );

	var template = _.template( require( 'text!./templates/login-template.html' ) );

	return Backbone.View.extend( {

		events: {},

		initialize: function ( options ) {
			this.render();
		},

		render: function () {

			var data = _.extend( {}, { translator: translator } );

			this.$el.html( template( data ) );

			return this;
		}
	} );
} );