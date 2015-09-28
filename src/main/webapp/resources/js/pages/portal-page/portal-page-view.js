define( function ( require ) {

	'use strict';

	var Backbone = require( 'backbone' );
	var _ = require( 'underscore' );
	var $ = require( 'jquery' );

	var template = _.template( require( 'text!./templates/portal-page-template.html' ) );

	var Translator = require( 'translator' );
	var t = new Translator( {
		title: ""
	} );

	return Backbone.View.extend( {

		events: {},

		initialize: function ( options ) {
			this.render();
		},

		render: function () {
			this.$el.html( template( { t : t } ) );
		}
	} );
} );