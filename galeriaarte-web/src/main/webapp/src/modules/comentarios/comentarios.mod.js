(function (ng) {
    // Definición del módulo
    var mod = ng.module("comentarioModule", ['ui.router']);
    mod.constant("comentarioContext", "api/comentarios");
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', 
        function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/comentarios/';
            // Mostrar la lista de editoriales será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/comentariosList");
            // Definición del estado 'editorialsList' donde se listan los editoriales
            $stateProvider.state('comentarios', {
                url: '/comentarios',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'comentarios.html',
                        controller: 'comentarioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('comentariosList', {
                // Url que aparecerá en el browser
                url: '/list',
                parent: 'comentarios',
                views: {
//                    'mainView': {
                    'listView': {
                        templateUrl: basePath + 'comentarios.list.html'
                    }
                }
            }).state('comentarioDetail', {
                url: '/{id:int}/detail',
                parent: 'comentarios',
                param: {
                    id: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'comentarios.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'comentarios.detail.html',
                        controller: 'comentarioCtrl',
                        controllerAs: 'ctrl'
                    }

                }

            });
        }
    ]);
})(window.angular);