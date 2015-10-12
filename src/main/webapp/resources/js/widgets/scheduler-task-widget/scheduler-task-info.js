define( function ( require ) {

	'use strict';

	var Backbone = require( 'backbone' );
	var _ = require( 'underscore' );
	var $ = require( 'jquery' );

	var template = _.template( require( 'text!./templates/scheduler-task-info-template.html' ) );

	var Translator = require( 'translator' );
	var t = new Translator( {
		menuItem_TaskExecutionHistoryLabel: "Task execution history"
	} );

	return Backbone.View.extend( {

		events: {
		},

		initialize: function ( options ) {
			this.model.on( 'sync', this._renderTaskInfo, this );
		},

		render: function() {
			this.model.fetch( { cache: false } );
		},

		title: function() {
			return this.model.get( 'taskName' );
		},

		icon: function() {
			return 'fa fa-calendar-check-o';
		},

		menuItems: function() {

			return [
				{ selector: 'js-menu-task-execution-history', icon: 'fa fa-history', link: '#', text: t.menuItem_TaskExecutionHistoryLabel }
			];
		},

		_renderTaskInfo: function() {
			this.$el.html( template( { t : t } ) );
			this.trigger( 'inner-view-rendered' );
		}
	} );
} );