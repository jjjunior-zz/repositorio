import Model from 'ember-data/model';
import DS from "ember-data";

export default Model.extend({
  description:DS.attr('string'),
  application:DS.attr('string'),
  operationalSystem:DS.attr('string'),
  processorArchitecture:DS.attr('string') 
});

