define( function( require ) {

	'use strict';

	var Backbone = require( 'backbone' );
	var _ = require( 'underscore' );
	var $ = require( 'jquery' );

	var Model = require( './portal-page-model' );
	var View = require( './portal-page-view' );

	function init( container, options ) {

		var model = new Model.PortalPageModel( { options: options } );
		var view = new View( { model: model, el: container, options: options } );

		return {

			view: function() {
				return view;
			}
		}
	}

	return init;
});
