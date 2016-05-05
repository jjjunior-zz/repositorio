import DS from 'ember-data';

export default DS.RESTSerializer.extend({
	
    keyForAttribute: function(attr) {
        return attr.capitalize();
    },

    keyForRelationship: function(attr) {
        return attr.capitalize();
    }
});
