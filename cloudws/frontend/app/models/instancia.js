import Model from 'ember-data/model';
import DS from "ember-data";

export default Model.extend({    
  processador: DS.attr('number'),
  memoria: DS.attr('number'),
  armazenamento:DS.attr('number'),
  status: DS.attr('string'),
  tipo: DS.attr('string')    
});
