define( function ( require ) {

	'use strict';

	var Backbone = require( 'backbone' );

	return Backbone.Model.extend( {

		defaults: {
			id: 0
			, userId: 0
			, userName: ''
		},

		initialize: function ( options ) {
			this.options = options.options;
			this.url = '/rest/portal-page/';
		}
	} );
} );
