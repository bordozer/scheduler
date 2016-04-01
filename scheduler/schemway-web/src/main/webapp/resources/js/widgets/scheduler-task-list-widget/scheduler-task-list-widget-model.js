define( function ( require ) {

	'use strict';

	var Backbone = require( 'backbone' );
	var _ = require( 'underscore' );

	return Backbone.Collection.extend( {

		defaults: {},

		initialize: function ( options ) {

		},

		url: function () {
			return '/rest/tasks/ids/';
		}
	} );
} );