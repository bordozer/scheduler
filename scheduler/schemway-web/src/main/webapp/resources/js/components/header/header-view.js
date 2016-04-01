define( function ( require ) {

	'use strict';

	var Backbone = require( 'backbone' );
	var _ = require( 'underscore' );
	var $ = require( 'jquery' );

	var template = _.template( require( 'text!./templates/header-template.html' ) );

	var HeaderView = Backbone.View.extend( {

		initialize: function( options ) {
			this.breadcrumbs = options.options.breadcrumbs;

			this.on( 'navigation:set:active:cup', this._setActiveCup, this );

			this.render();
		},

		render: function () {

			var title = this.breadcrumbs[ this.breadcrumbs.length - 1 ].title;

			this.$el.html( template( {
				model: this.model
				, title: title
				, breadcrumbs: this.breadcrumbs
			} ) );

			return this;
		}
	} );

	return { HeaderView: HeaderView };
} );

