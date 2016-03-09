define( function ( require ) {

	'use strict';

	var Backbone = require( 'backbone' );
	var _ = require( 'underscore' );
	var $ = require( 'jquery' );

	var userPageTemplate = _.template( require( 'text!./templates/user-base-page-template.html' ) );

	var PageView = require( 'js/components/base-view/base-page-view' );

	var Translator = require( 'translator' );
	var t = new Translator( {
		menuAdminLabel: "Administration"
		, menuUsersLabel: 'Users'
		, menuYourGroupsLabel: 'Your groups'
		, menuPersonalDataLabel: 'User settings'
		, menuLogoutLabel: 'Logout'
		, logoutConfirmation: 'Logout?'
		, error: 'Error!'
	} );

	return PageView.extend( {

		builtinEvents: {
			'click .js-logout': 'logout'
		},

		renderBody: function() {

			this.$( '.js-body-view-container' ).html( userPageTemplate() );

			this.bodyView = this.bodyRenderer( this.$( '.js-custom-view' ), this.options ).view();
			this.bodyView.on( 'navigation:set:active:cup', this._setActiveCup, this );
		},

		_setActiveCup: function( options ) {
			this.headerView.trigger( 'navigation:set:active:cup', options );
		},

		mainMenuItems: function() {
			return [
				{ selector: 'js-logout',
					icon: 'fa fa-sign-out',
					link: '#',
					text: t.menuLogoutLabel
				}
			];
		},

		logout: function () {

			if ( ! confirm( t.logoutConfirmation ) ) {
				return;
			}

			$.ajax( {
				method: 'POST',
				url: '/logout',
				success: function ( response ) {
					window.location.reload();
				},
				error: function() {
					alert( t.error );
				}
			} )
		}
	});
} );
