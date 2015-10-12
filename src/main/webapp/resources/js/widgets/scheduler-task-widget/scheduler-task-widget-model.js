define( function ( require ) {

	'use strict';

	var Backbone = require( 'backbone' );
	var _ = require( 'underscore' );

	return Backbone.Model.extend( {

		defaults: {},

		initialize: function ( options ) {
			this.taskId = options.options.taskId;
		},

		url: function () {
			return '/rest/tasks/' + this.taskId + '/';
		}
	} );
} );