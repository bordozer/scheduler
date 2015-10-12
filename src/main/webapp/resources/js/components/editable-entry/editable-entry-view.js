define( function ( require ) {

	'use strict';

	var _ = require( 'underscore' );
	var $ = require( 'jquery' );

	var WidgetView = require( 'js/components/widget/widget-view' );

	var Translator = require( 'translator' );
	var t = new Translator( {
		menuItemEditLabel: "Edit"
		, menuItemSaveLabel: "Save"
		, menuItemDiscardEditingLabel: "Discard"
	} );

	var MODE_INFO = 1;
	var MODE_EDIT = 2;

	return WidgetView.extend( {

		viewMode: MODE_INFO,

		events: {
			'click .js-menu-task-edit': '_onEditTaskClick'
			, 'click .js-menu-task-edit-save': '_onSaveTaskClick'
			, 'click .js-menu-task-discard-editing': '_onDiscardTaskEditingClick'
		},

		initialize: function ( options ) {

			this.viewInfo = options.viewInfo;
			this.viewEdit = options.viewEdit;

			this.render();
		},

		renderBody: function() {

			var view = this.viewMode == MODE_INFO ? this.viewInfo : this.viewEdit ;

			view.$el = this.$bel;
			view.on( 'inner-view-rendered', this.renderBodyFinished, this );
			view.render();
		},

		getTitle: function() {

			if ( this.viewMode == MODE_EDIT ) {
				return this.viewInfo.title() + ' - ' + t.menuItemEditLabel;
			}

			return this.viewInfo.title();
		},

		getIcon: function() {
			return this.viewInfo.icon();
		},

		getCustomMenuItems: function() {

			if ( this.viewMode == MODE_EDIT ) {

				return [
					{ selector: 'js-menu-task-edit-save', icon: 'fa fa-save', link: '#', text: t.menuItemSaveLabel }
					, { selector: 'js-menu-task-discard-editing', icon: 'fa fa-close', link: '#', text: t.menuItemDiscardEditingLabel }
				];
			}

			return [
				{ selector: 'js-menu-task-edit', icon: 'fa fa-edit', link: '#', text: t.menuItemEditLabel }
			];
		},

		_onEditTaskClick: function() {
			this.viewMode = MODE_EDIT;
			this.render();
		},

		_onSaveTaskClick: function() {
			this.viewMode = MODE_INFO;
			this.render();
		},

		_onDiscardTaskEditingClick: function() {
			this.viewMode = MODE_INFO;
			this.render();
		}
	} );
} );