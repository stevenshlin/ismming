/* globals define */
define(function (require) {
  'use strict';

  var Backbone = require('backbone'),
    categoriesTemplate = require('hbs!../templates/Categories');

  return Backbone.View.extend({

    tagName: 'div',
    className: 'col-lg-6',
    template: categoriesTemplate,

    render: function () {
      var categories_data = [
        {'name': 'COLLECTION ONE', 'description': 'collection one produced by ismming.com'},
        {'name': 'COLLECTION TWO', 'description': 'collection two produced by ismming.com'},
        {'name': 'COLLECTION THREE', 'description': 'collection three produced by ismming.com'}
      ];
      this.$el.html(this.template({categories: categories_data}));
      return this;
    }
  });
});
