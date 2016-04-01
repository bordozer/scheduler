define( function ( require ) {

	'use strict';

	var _ = require( 'underscore' );
	var bootbox = require( 'bootbox' );

	var Translator = require( 'translator' );
	var t = new Translator( {
		validationTitle: "Validation error"
	} );

	return {

		dialogInfo: function( title, message ) {

			bootbox.dialog( {
				title: title
				, message: message
			} );
		},

		dialogValidationError: function( message ) {
			this.dialogInfo( t.validationTitle, message );
		}
	}
} );