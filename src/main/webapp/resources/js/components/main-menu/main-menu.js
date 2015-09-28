define( function( require ) {

	'use strict';

	var Backbone = require( 'backbone' );
	var _ = require( 'underscore' );
	var $ = require( 'jquery' );

	var Model = require( './main-menu-model' );
	var View = require( './main-menu-view' );

	function init( options, container ) {

		var model = new Model();
		var view = new View( { model: model, el: container, options: options } );

		return view.render();
	}

	return init;
});
