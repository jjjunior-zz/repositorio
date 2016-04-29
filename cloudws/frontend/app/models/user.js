import Model from 'ember-data/model';
import DS from "ember-data";

export default Model.extend({
  email:DS.attr('string'),
  password:DS.attr('string'),
  userType:DS.attr('string')  
});

