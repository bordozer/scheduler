define( function ( require ) {

	var Backbone = require( 'backbone' );
	var _ = require( 'underscore' );
	var $ = require( 'jquery' );

	var data = {
		projectName: ""
		, currentUser: {
				userId: 0
				, userName: ''
			}
	};

	var AppDataLoader = function () {
		this.initialize();
	};

	_.extend( AppDataLoader.prototype, Backbone.Events, {

		initialize: function () {
			this._load();
		},

		projectName: function() {
			return data.projectName;
		},

		currentUser: function() {
			return data.currentUser;
		},

		_load: function() {

			var clientData = {
				timezone: new Date().getTimezoneOffset() / 60
			};

			$.ajax( {
				method: 'GET',
				url: '/rest/app/',
				data: clientData,
				async: false,
				success: function ( response ) {
					data = response;
				},
				error: function() {
				}
			} );
		}
	} );

	return new AppDataLoader();
});
