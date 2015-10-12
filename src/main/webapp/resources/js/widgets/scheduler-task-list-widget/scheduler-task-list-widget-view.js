define( function ( require ) {

	'use strict';

	var Backbone = require( 'backbone' );
	var _ = require( 'underscore' );
	var $ = require( 'jquery' );

	var WidgetView = require( 'js/components/widget/widget-view' );
	var schedulerTaskWidget = require( 'js/widgets/scheduler-task-widget/scheduler-task-widget' );

	var template = _.template( require( 'text!./templates/scheduler-task-list-template.html' ) );

	var Translator = require( 'translator' );
	var t = new Translator( {
		title: "Scheduler tasks"
	} );

	return WidgetView.extend( {

		events: {},

		initialize: function ( options ) {
			this.model.on( 'sync', this.__renderTaskList, this );
			this.render();
		},

		renderBody: function() {
			this.model.fetch( { cache: false } );
		},

		getTitle: function() {
			return t.title;
		},

		getIcon: function() {
			return 'fa fa-list-alt';
		},

		__renderTaskList: function() {

			var $bel = this.$bel;

			$bel.html( template( { t : t } ) );

			var el = $( "<div class='col-xs-12 col-lg-3'></div>" );
			$bel.append( el );

			schedulerTaskWidget( el, {} );

			this.renderBodyFinished();
		}
	} );
} );