import DS from 'ember-data';

export default DS.RESTSerializer.extend({
  normalizeResponse(store, primaryModelClass, payload, id, requestType) {
    payload = {
      instancias: payload,
      template: payload,
      user:payload
    };
    return this._super(store, primaryModelClass, payload, id, requestType);
  }
});