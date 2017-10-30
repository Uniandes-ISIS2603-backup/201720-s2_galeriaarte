(function (ng) {
    // Definición del módulo
    var mod = ng.module("galeriaModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modulues/galerias/';
            // Mostrar la lista de blogs será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/galeriasList");
            // Definición del estado 'blogsList' donde se listan los blogs
            $stateProvider.state('galeriasList', {
                // Url que aparecerá en el browser
                url: '/galerias/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'galerias.list.html',
                        controller: 'galeriaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
