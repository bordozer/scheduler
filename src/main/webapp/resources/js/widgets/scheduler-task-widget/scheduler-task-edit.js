define( function ( require ) {

	'use strict';

	var Backbone = require( 'backbone' );
	var _ = require( 'underscore' );
	var $ = require( 'jquery' );

	var template = _.template( require( 'text!./templates/scheduler-task-edit-template.html' ) );

	var Translator = require( 'translator' );
	var t = new Translator( {
		taskName: "Task name"
	} );

	return Backbone.View.extend( {

		events: {},

		initialize: function ( options ) {
			this.model.on( 'sync', this._renderTaskEdit, this );
		},

		render: function () {
			this.model.fetch( { cache: false } );
		},

		_renderTaskEdit: function () {

			this.$el.html( template( {
				model: this.model.toJSON()
				, t: t
			} ) );

			this.trigger( 'inner-view-rendered' );
		},

		menuItems: function () {
			return [];
		},

		save: function () {
			console.log( 'save' ); // TODO
		}
	} );
} );