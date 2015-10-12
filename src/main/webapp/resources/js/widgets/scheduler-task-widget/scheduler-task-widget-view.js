define( function ( require ) {

	'use strict';

	var Backbone = require( 'backbone' );
	var _ = require( 'underscore' );
	var $ = require( 'jquery' );

	var WidgetView = require( 'js/components/widget/widget-view' );

	var template = _.template( require( 'text!./templates/scheduler-task-template.html' ) );

	var Translator = require( 'translator' );
	var t = new Translator( {
		menuItemTaskEditLabel: "Edit scheduler task"
	} );

	return WidgetView.extend( {

		events: {
			'click .js-menu-task-edit': '_onEditTaskClick'
		},

		initialize: function ( options ) {
			this.model.on( 'sync', this._renderTask, this );
			this.render();
		},

		renderBody: function() {
			this.model.fetch( { cache: false } );
		},

		getTitle: function() {
			return this.model.get( 'taskName' );
		},

		getIcon: function() {
			return 'fa fa-list-alt';
		},

		getCustomMenuItems: function() {

			return [
				{ selector: 'js-menu-task-edit', icon: 'fa fa-edit', link: '#', text: t.menuItemTaskEditLabel }
			];
		},

		_renderTask: function() {

			this.$bel.html( template( { t : t } ) );

			this.renderBodyFinished();
		},

		_onEditTaskClick: function() {

		}
	} );
} );