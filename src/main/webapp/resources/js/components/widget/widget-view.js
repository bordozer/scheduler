define( function ( require ) {

	'use strict';

	var Backbone = require( 'backbone' );
	var _ = require( 'underscore' );
	var $ = require( 'jquery' );

	var template = _.template( require( 'text!./templates/widget-template.html' ) );

	var mainMenu = require( 'js/components/main-menu/main-menu' );

	var Translator = require( 'translator' );
	var t = new Translator( {
		loadingLabel: 'Loading...'
		, menuItemRefreshLabel: 'Refresh'
		, widgetMenuHint: 'Widget menu'
	} );

	return Backbone.View.extend( {

		progressIcon: 'fa fa-circle-o-notch fa-spin',
		windowBodyContainerSelector: '.js-widget-body',

		builtinEvents: {
			'click .js-menu-refresh': '_onMenuRefreshClick'
		},

		constructor: function ( options ) {

			this.events = _.extend( this.builtinEvents, this.events );

			this.on( 'view:render', this.render, this );

			Backbone.View.apply( this, [ options ] );
		},

		render: function() {

			this.$el.html( template( {
				icon: this.getIcon()
				, minHeight: this.widgetBodyMinHeight()
				, t: t
			} ) );

			this.$bel = this.$( this.windowBodyContainerSelector );

			this._showProgress();

			this.$bel.fadeIn( 500, "swing" );

			this.renderBody();

			this.delegateEvents();
		},

		getTitle: function() {
			return '...';
		},

		getIcon: function() {
			return 'fa fa-windows';
		},

		renderBody: function() {
			// render custom widget context here
		},

		widgetBodyMinHeight: function() {
			return '50px';
		},

		_showProgress: function() {
			var el = this._getIconEl();
			el.html( '' );
			el.removeClass( this.getIcon() );
			el.addClass( this.progressIcon );
		},

		_hideProgress: function() {

			var el = this._getIconEl();
			el.html( '' );
			el.removeClass( this.progressIcon );
			el.addClass( this.getIcon() );
		},

		getCustomMenuItems: function() {
			return [];
		},

		setFooterHtml: function( html ) {
			this.$( '.js-footer' ).html( html );
		},

		setPanelClass: function( clazz ) {
			var panel = this.$( '.panel' );
			panel.removeClass( 'panel-default panel-info panel-success panel-warning panel-danger' );
			panel.addClass( clazz )
		},

		_renderDropDownMenu: function() {

			var menuItems =  [
				{ selector: 'js-menu-refresh', icon: 'fa fa-refresh', link: '#', text: t.menuItemRefreshLabel }
			];

			var customMenuItems = this.getCustomMenuItems();

			if ( customMenuItems.length > 0 ) {
				menuItems = menuItems.concat( [ { selector: 'divider' } ] );
			}

			menuItems = menuItems.concat( customMenuItems );

			var menuEl = this.$( '.js-window-drop-down-menu' );

			var options = {
				menus: menuItems
				, menuButtonIcon: this.getIcon()
				, menuButtonText: ''
				, menuButtonHint: t.widgetMenuHint
				, cssClass: ( menuEl.offset() && menuEl.offset().left > 400 ? 'dropdown-menu-right ' : '' ) + 'btn-default'
			};

			mainMenu( options, menuEl );
		},

		renderBodyFinished: function() {

			this._hideProgress();

			var title = this.$( '.js-widget-title' );

			if ( title.length > 1 ) {
				title = $( title[0] ); // hack for FearFox
			}

			title.html( this.getTitle() );

			this._renderDropDownMenu();
		},

		_getIconEl: function() {
			return this.$( '.js-window-icon' );
		},

		_onMenuRefreshClick: function() {
			this.render();
		}
	});
} );
