(function (ng) {
    var mod = ng.module("marcoModule");
    mod.constant("marcosContext", "api/marcos");
    mod.controller('marcoNewCtrl', ['$scope', '$http', 'marcosContext', '$state','$rootScope',
        function ($scope, $http, marcosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createMarco = function () {
                $http.post(marcosContext, {
                    name: $scope.marcoName,
                    material: $scope.marcoMaterial,
                    alto: $scope.marcoAlto,
                    ancho: $scope.marcoAncho,
                    image: $scope.marcoImage,
                    valor: $scope.marcoValor
                }).then(function (response) {
                    //Marco created successfully
                    $state.go('marcosList', {marcoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);

