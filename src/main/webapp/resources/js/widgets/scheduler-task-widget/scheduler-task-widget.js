define( function ( require ) {

	'use strict';

	var Backbone = require( 'backbone' );
	var _ = require( 'underscore' );
	var $ = require( 'jquery' );

	var Model = require( './scheduler-task-widget-model' );

	var EditableEntry = require( 'js/components/editable-entry/editable-entry-view' );

	var ViewInfo = require( './scheduler-task-info' );
	var ViewEdit = require( './scheduler-task-edit' );

	function init( container, options ) {

		var model = new Model( { options: options } );

		//var viewInfo = new ViewInfo( { model: model, el: container, options: options } );
		//var viewEdit = new ViewEdit( { model: model, el: container, options: options } );

		var view = new EditableEntry( {
			model: model
			, el: container
			, viewInfo: new ViewInfo( { model: model } )
			, viewEdit: new ViewEdit( { model: model } )
		} );
	}

	return init;
} );