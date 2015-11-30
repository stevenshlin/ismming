/* globals define */
define(function (require) {
  'use strict';

  var Backbone = require('backbone'),
    homeTemplate = require('hbs!../templates/Home');

  return Backbone.View.extend({

    template: homeTemplate,

    render: function () {
      var CategoriesView = require('../js/CategoriesView');
      this.$el.html(this.template());
      this.$el.find('.row').html(new CategoriesView().render().el);
      return this;
    }
  });
});
