(function (ng) {
    // Definición del módulo
    var mod = ng.module("comentarioModule", ['ui.router']);
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', 
        function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/comentarios/';
            // Mostrar la lista de editoriales será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/comentariosList");
            // Definición del estado 'editorialsList' donde se listan los editoriales
            $stateProvider.state('comentariosList', {
                // Url que aparecerá en el browser
                url: '/comentarios/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'comentarios.list.html',
                        controller: 'comentarioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);