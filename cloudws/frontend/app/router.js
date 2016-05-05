import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType
});

Router.map(function() {
  this.route('instance');
  this.route('instance', { path: '/instance/:instance_id' });
  this.route('template');
  this.route('user');
});

export default Router;
