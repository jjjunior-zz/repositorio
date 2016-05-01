import Model from 'ember-data/model';
import DS from "ember-data";

export default Model.extend({
  name:DS.attr('string'),
  cpu:DS.attr('number'),
  memory: DS.attr('number'),
  storage:DS.attr('number'),
  cpuType:DS.attr('string'),
  status: DS.attr('string'),
  template: DS.belongsTo('template',{ async: false }),
  user: DS.belongsTo('user',{ async: false })  
});
