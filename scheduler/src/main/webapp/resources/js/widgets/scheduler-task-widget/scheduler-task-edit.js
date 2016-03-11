define( function ( require ) {

	'use strict';

	var Backbone = require( 'backbone' );
	var _ = require( 'underscore' );
	var $ = require( 'jquery' );

	var template = _.template( require( 'text!./templates/scheduler-task-edit-template.html' ) );

	var Translator = require( 'translator' );
	var t = new Translator( {
		taskName: "Task name",
		taskType: "Task type",
		taskDescription: "Description",
		taskParametersJSON: "Task parameters JSON",
		taskUrl: "Url"
	} );

	return Backbone.View.extend( {

		events: {},

		initialize: function ( options ) {
			this.model.on( 'sync', this._renderTaskEdit, this );
		},

		render: function () {
			this.model.fetch( { cache: false } );
		},

		_renderTaskEdit: function () {
			this.$el.html( template( {
				model: this.model.toJSON()
				, t: t
			} ) );

			this.trigger( 'inner-view-rendered' );
		},

		menuItems: function () {
			return [];
		},

		save: function () {
			this._bind();
			//console.log( 'save' ); // TODO
		},

		_bind: function() {
			var model = this.model.toJSON();
			console.log('before save', model);
			this.model.set({
				taskName: this.$("input[name='taskName']").val(),
				taskType: this.$("input[name='schedulerTaskType']:checked").val(),
				taskDescription: this.$("input[name='taskDescription']").text(),
				taskParametersJSON: this.$("input[name='taskParametersJSON']").text(),
				remoteJob: {
					remoteJobId: model.remoteJob.remoteJobId,
					requestUrl: '',
					requestMethod: '',
					authString: '',
					postJson: ''
				}
			});
			console.log('after save', this.model.toJSON());
		}
	} );
} );