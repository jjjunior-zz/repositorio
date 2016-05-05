import DS from 'ember-data';

export default DS.RESTSerializer.extend(DS.EmbeddedRecordsMixin, {  		
	isNewSerializerAPI: true,

	normalize(modelClass, resourceHash, prop) { 
		return this._super(modelClass, resourceHash.data, prop);
	},
  	
	attrs: {			
		templates:{embedded:'always'},
		users:{embedded:'always'} 
	},
});


