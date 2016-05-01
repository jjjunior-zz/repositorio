import Ember from 'ember';

export default Ember.Route.extend({
	model(params) {

  		if (params.instancia_id != null) {
  			console.log(params);
  			console.log(params.instancia_id);
  			return this.store.findRecord('template', params.template_id);	
  	}else{  		
  		return this.store.findAll('template');	
  	}    
  }  
});
