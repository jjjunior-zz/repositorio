import Ember from 'ember';

export default Ember.Route.extend({
  model(params) {

  	if (params.instance_id != null) {  	
  	 	console.log(  params.instance_id);
  		var instance = this.store.findRecord('instance', params.instance_id);	  		
  		return instance;
  	}else{        
  		return this.store.findAll('instance');	
  	}    
  }  
});


