export const environment = {
  production: false,
  api: {
    institutions: {
      //Uses a proxy configuration for sandbox . Refer the proxy.conf.json under src
      url: 'api/pentadata/institutions/search?searchTxt=',
      version: 'application/json',
    },
    person:{
      create: {
        url : 'api/pentadata/person/?userId=',
        version: 'application/json'
      },
      consent_banking:{
        url : 'api/pentadata/person/consent/signature/{personId}/banking',
        version: 'application/json'
      },
      retrieve: {
        url : 'api/pentadata/person/?emailId=',
        version: 'application/json'
      },
    },
    account:{
      retrieve:{
        url: 'api/pentadata/accounts/{personId}',
        version: 'application/json',
      },
      create:{
      url: 'api/pentadata/accounts/',
      version: 'application/json',
      redirect_uri: 'http://www.google.com'
    },
    transactions:{
      url: 'api/pentadata/accounts/{accountId}/transactions',
      version: 'application/json',
    }

    },
    consent:{
      version: 'application/json'
    },
    user:{
      url:'api/pentadata/user/?emailId=',
      version: 'application/json'
    }
  } //end of api
};
