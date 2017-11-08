(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogosContext", "api/catalogos");
    mod.controller('catalogoDeleteCtrl', ['$scope', '$http', 'catalogosContext', '$state',
        function ($scope, $http, catalogosContext, $state) {
            var idCatalogo = $state.params.catalogoId;
            $scope.deleteCatalogo = function () {
                $http.delete(catalogosContext + '/' + idCatalogo, {}).then(function (response) {
                    $state.go('catalogosList', {catalogoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);
