// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  api: {
    institutions: {
      url: '',
      version: 'application/json'
    },
    person:{
      create: {
        url : '',
        version: 'application/json'
      },
      retrieve: {
        url : '',
        version: 'application/json'
      },
      consent_banking:{
        url : '',
        version: 'application/json'
      }
  },
    account:{
      create:{
      url: '',
      version: 'application/json',
        redirect_uri: ''
    },
      retrieve:{
        url: 'api/pentadata/accounts/',
        version: 'application/json',
      },
      transactions:{
        url: 'api/pentadata/accounts/{accountId}/transactions',
        version: 'application/json',
      }
    },
    consent:{
      url:'',
      version: 'application/json'
    },
    user:{
      url:'',
      version: 'application/json'
    }
    } //end of api
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
