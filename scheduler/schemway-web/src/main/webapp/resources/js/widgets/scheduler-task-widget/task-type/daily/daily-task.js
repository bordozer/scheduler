define( function ( require ) {

    'use strict';

    var Backbone = require( 'backbone' );
    var _ = require( 'underscore' );
    var $ = require( 'jquery' );

    var template = _.template( require( 'text!./templates/daily-task-template.html' ) );

    var Translator = require( 'translator' );
    var t = new Translator( {
        taskName: "TODO"
    } );

    return Backbone.View.extend( {

        events: {
        },

        initialize: function ( options ) {
            this.$el.html(template());
        }
    } );
} );