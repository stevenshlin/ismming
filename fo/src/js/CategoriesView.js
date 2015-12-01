/* globals define */
define(function (require) {
  'use strict';

  var Backbone = require('backbone'),
    api = require('../js/Api'),
    categoriesTemplate = require('hbs!../templates/Categories');

  return Backbone.View.extend({

    tagName: 'div',
    className: 'col-lg-6',
    template: categoriesTemplate,

    render: function () {
      var _this = this;
      this.getCategories().done(function (data) {
        _this.$el.html(_this.template({categories: data}));
      }).fail(function () {
        _this.$el.html('invoke api failed');
      });
      return this;
    },

    getCategories: function () {
      return api.get({
        uri: '/category'
      });
    }
  });
});
