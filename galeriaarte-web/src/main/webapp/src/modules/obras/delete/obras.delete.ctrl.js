(function (ng) {
    var mod = ng.module("obraModule");
    mod.constant("obrasContext", "api/obras");
    mod.controller('obraDeleteCtrl', ['$scope', '$http', 'obrasContext', '$state',
        function ($scope, $http, obrasContext, $state) {
            var idObra = $state.params.obraId;
            $scope.deleteObra = function () {
                $http.delete(obrasContext + '/' + idObra, {}).then(function (response) {
                    $state.go('obrasList', {obraId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);

