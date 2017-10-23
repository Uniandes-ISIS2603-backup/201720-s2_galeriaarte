(function (ng) {
    var mod = ng.module("editorialModule");
    mod.constant("editorialContext", "api/editorials");
    mod.controller('editorialCtrl', ['$scope', '$http', 'editorialContext',
        function ($scope, $http, editorialContext) {
            //'data/clientes.json'  la ubicacion donde esta el archivo de pruebas
            $http.get('data/clientes.json').then(function (response) {
                $scope.editorialsRecords = response.data;
            });
        }
    ]);
}
)(windows.angular);