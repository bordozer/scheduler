define( function ( require ) {

	'use strict';

	var Backbone = require( 'backbone' );
	var _ = require('underscore');

	return Backbone.Model.extend( {

		initialize: function ( nerds ) {
			this.parse( { translations: nerds } );	// TODO: disable when translator works
			//this._loadTranslations( nerds ); 		// TODO: enable when translator works
		},

		url: '/rest/translator/',

		defaults: {
			translations: {}
		},

		parse: function ( data ) {
			_.each( data.translations, function ( value, key) {
				this[key] = value;
			}, this );
		},

		_loadTranslations: function( nerds ) {
			this.fetch( { data: { translations: nerds }, cache: false, async: false } );
		}
	});
});
