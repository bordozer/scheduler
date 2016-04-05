define( function ( require ) {

	'use strict';

	var Backbone = require( 'backbone' );
	var _ = require( 'underscore' );
	var $ = require( 'jquery' );

	var template = _.template( require( 'text!./templates/scheduler-task-edit-template.html' ) );

	var OneTimeTaskVew = require( './task-type/one-time/one-time-task' );
	var DailyTaskVew = require( './task-type/daily/daily-task' );
	var WeeklyTaskVew = require( './task-type/weekly/weekly-task' );
	var MonthlyTaskVew = require( './task-type/monthly/monthly-task' );

	var Translator = require( 'translator' );
	var t = new Translator( {
		taskName: "Task name",
		taskType: "Task type",
		taskDescription: "Description",
		taskParametersJSON: "Task parameters JSON",
		requestUrl: "Request URL",
		requestMethod: "Request method",
		authString: "Auth string",
		postJson: "Json post to"
	} );

	return Backbone.View.extend( {

		events: {
			"change input[name='taskType']": '__render',
			'change .js-request-method': '__render'
		},

		initialize: function ( options ) {
			this.model.on( 'sync', this.__renderTaskEdit, this );
		},

		render: function () {
			this.model.fetch( { cache: false } );
		},

		menuItems: function () {
			return [];
		},

		save: function () {
			this._bind();
			this.model.save();
		},

		__renderTaskEdit: function () {
			var json = this.model.toJSON();
            this.$el.html( template( {
				model: json
				, t: t
			} ) );

			var container = this.$('.js-task-type-specific-parameters');
            if (json.taskType === 'ONE_TIME') {
				new OneTimeTaskVew({el: container});
			}

			if (json.taskType === 'DAILY') {
				new DailyTaskVew({el: container});
			}

			if (json.taskType === 'WEEKLY') {
				new WeeklyTaskVew({el: container});
			}

			if (json.taskType === 'MONTHLY') {
				new MonthlyTaskVew({el: container});
			}

			this.trigger( 'inner-view-rendered' );
		},

		__render: function() {
			console.log('__render');
			this._bind();
			this.__renderTaskEdit();
		},

		_bind: function() {
			var model = this.model.toJSON();
			this.model.set({
				taskName: this.$("input[name='taskName']").val(),
				taskType: this.$("input[name='taskType']:checked").val(),
				taskDescription: this.$("textarea[name='taskDescription']").val(),
				taskParametersJSON: this.$("textarea[name='taskParametersJSON']").val(),
				remoteJob: {
					remoteJobId: model.remoteJob.remoteJobId,
					requestUrl: this.$("input[name='requestUrl']").val(),
					requestMethod: this.$("input[name='requestMethod']:checked").val(),
					authString: this.$("input[name='authString']").val(),
					postJson: this.$("textarea[name='postJson']").val() || model.remoteJob.postJson
				}
			});
			console.log(this.model.toJSON());
		}
	} );
} );