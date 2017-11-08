(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogosContext", "api/catalogos");
    mod.controller('catalogoNewCtrl', ['$scope', '$http', 'catalogosContext', '$state','$rootScope',
        function ($scope, $http, catalogosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createCatalogo = function () {
                $http.post(catalogosContext, {
                    categoria: $scope.categoriaCatalogo
                }).then(function (response) {
                    //Blog created successfully
                    $state.go('catalogosList', {catalogoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);
