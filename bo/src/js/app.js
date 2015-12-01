/* globals define */
define(function (require) {
  'use strict';

  var App = (function () {

    // constructor
    function App() {

      // context ref
      var _this = this;

      // new backbone router
      this.Router = new (this.Backbone.Router.extend({

        routes: {
          '': 'home',
          '*notFound': 'notFound'
        },

        home: function () {
          var HomeView = require('../js/HomeView');
          new HomeView({
            'el': _this.$('#main-container')
          }).render();
        },

        notFound: function () {
          _this.Router.navigate('#/');
        }

      }))();

      // history backbone start
      this.Backbone.history.start();
    }

    // VERSION
    App.prototype.VERSION = '0.0.0';

    // Backbone
    App.prototype.Backbone = require('backbone');

    // underscore
    App.prototype._ = require('underscore');

    // jQuery
    App.prototype.$ = require('jquery');

    // Config
    App.prototype.CONFIG = require('../js/Config');

    // return App
    return App;

  })();

  return (new App());
});
