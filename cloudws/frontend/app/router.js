import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType
});

Router.map(function() {
  this.route('instancia');
  this.route('instancia', { path: '/instancia/:instancia_id' });
  this.route('template');
  this.route('user');
});

export default Router;
