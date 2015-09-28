define( function ( require ) {

	'use strict';

	var Backbone = require( 'backbone' );
	var _ = require( 'underscore' );
	var $ = require( 'jquery' );

	var WidgetView = require( 'js/components/widget/widget-view' );

	var template = _.template( require( 'text!./templates/scheduler-task-template.html' ) );

	var Translator = require( 'translator' );
	var t = new Translator( {
		title: "Scheduler task"
	} );

	return WidgetView.extend( {

		events: {},

		initialize: function ( options ) {
			this.render();
		},

		getTitle: function() {
			return t.title;
		},

		getIcon: function() {
			return 'fa fa-list-alt';
		},

		renderBody: function() {
			this.$bel().html( template( { t : t } ) );

			this.renderBodyFinished();
		}
	} );
} );