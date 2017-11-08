(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogosContext", "api/catalogos");
    mod.controller('catalogoCtrl', ['$scope', '$http', 'catalogosContext', '$state',
        function ($scope, $http, catalogosContext, $state) {
            $http.get(catalogosContext).then(function (response) {
                $scope.catalogosRecords = response.data;
            });

            if (($state.params.catalogoId !== undefined) && ($state.params.catalogoId !== null)) {
                $http.get(catalogosContext + '/' + $state.params.catalogoId).then(function (response) {
                    $scope.currentCatalogo = response.data;
                });
            }
        }
    ]);
}
)(window.angular);

