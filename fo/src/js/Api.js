/* globals define */
define(function (require) {
  'use strict';

  var $ = require('jquery'),
    config = require('../js/Config');

  return {
    get: function (options) {
      var defer = $.Deferred();

      var request = {
        type: 'GET',
        url: config.host.api + '/api' + options.uri,
        cache: false,
        dataType: 'json'
      };

      if (options.data) {
        request.url += '?' + $.param(options.data);
      }

      $.ajax(request).done(function (responseJSON) {
        defer.resolve(responseJSON);
      }).fail(function (jqXHR) {
        if (jqXHR.status === 500) {
          console.log(jqXHR.responseText);
        }
        defer.reject(jqXHR);
      });

      return defer.promise();
    },

    post: function (options) {
      var defer = $.Deferred();

      var request = {
        type: 'POST',
        url: config.host.dev + '/api' + options.uri,
        cache: false,
        dataType: 'json'
      };

      if (options.contentType === 'json') {
        request.contentType = 'application/json; charset=utf-8';
        request.data = JSON.stringify(options.data);
      } else {
        request.contentType = 'application/x-www-form-urlencoded; charset=UTF-8';
        if (options.data) {
          request.data = options.data;
        }
      }

      if (options.type) {
        request.type = options.type;
      }

      $.ajax(request).done(function (responseJSON) {
        defer.resolve(responseJSON);
      }).fail(function (jqXHR) {
        if (jqXHR.status === 500) {
          console.log(jqXHR.responseText);
        }
        defer.reject(jqXHR);
      });

      return defer.promise();
    },

    put: function (options) {
      options.type = 'PUT';
      return this.post(options);
    }
  };
});
