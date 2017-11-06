(
        function (ng) {
            var mod = ng.module("clienteModule");
            mod.constant("clientesContext", "api/cliente");
            mod.constant("comentariosContext", "api/comentarios");
            mod.controller('clienteUpdateCtrl', ['$scope', '$http', 'clientesContext', '$state', 'comentariosContext', '$rootScope', '$filter',
                function ($scope, $http, clientesContext, $state, comentariosContext, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idCliente = $state.params.clienteId;

                    // Este arreglo guardara los ids de los books asociados y por asociar al autor.
                    var idsComentario = [];

                    // Este arreglo mostrará los books una vez esten filtrados visualmente por lo que el autor ya tiene asociado.
                    $scope.allComentariosShow = [];

                    //Consulto el autor a editar.
                    $http.get(clientesContext + '/' + idCliente).then(function (response) {
                        var cliente = response.data;
                        $scope.clienteName = cliente.name;
                        $scope.clienteTipoTarjeta = cliente.tipoTarjeta;
                        $scope.clienteNumeroTarjeta = cliente.numTarjeta;
                        $scope.allComentariosCliente = cliente.comentarios;
                        $scope.mergeComentarios($scope.allComentariosCliente);
                    });

                    /*
                     * Esta función añade los ids de los books que ya tiene el autor asociado.
                     * @param {type} books: Son los books que ya tiene asociado el autor.
                     * @returns {undefined}
                     */
                    $scope.mergeComentarios = function (comentarios) {
                        for (var item in comentarios) {
                            idsComentario.push("" + comentarios[item].id);
                        }
                        $scope.getComentarios(comentarios);
                    };

                    /*
                     * Esta función recibe como param los books que tiene el autor para hacer un filtro visual con todos los books que existen.
                     * @param {type} books
                     * @returns {undefined}
                     */
                    $scope.getComentarios = function (comentarios) {
                        $http.get(comentariosContext).then(function (response) {
                            $scope.Allcomentarios = response.data;
                            $scope.comentariosCliente = comentarios;

                            var filteredComentarios = $scope.Allcomentarios.filter(function (Allcomentarios) {
                                return $scope.comentariosCliente.filter(function (comentariosCliente) {
                                    return comentariosCliente.id == Allcomentarios.id;
                                }).length == 0
                            });

                            $scope.allComentariosShow = filteredComentarios;

                        });
                    };


                    //funciones para el drag and drop de HTML5 nativo
                    $scope.allowDrop = function (ev) {
                        ev.preventDefault();
                    };

                    $scope.drag = function (ev) {
                        ev.dataTransfer.setData("text", ev.target.id);
                    };

                    $scope.dropAdd = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                        //Cuando un book se añade al autor, se almacena su id en el array idsBook
                        idsComentario.push("" + data);
                    };

                    $scope.dropDelete = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                        //Para remover el book que no se va asociar, por eso se usa el splice que quita el id del book en el array idsBook
                        var index = idsComentario.indexOf(data);
                        if (index > -1) {
                            idsComentario.splice(index, 1);
                        }
                    };

                    $scope.createCliente = function () {
                        /*Se llama a la función newBooks() para buscar cada uno de los ids de los books
                         en el array que tiene todos los books y así saber como queda la lista final de los books asociados al autor.
                         */
                        $scope.newComentarios();
                        $http.put(clientesContext + "/" + idCliente, {
//                            id: 0,
                            name: $scope.clienteName,
                            tipoTarjeta: $scope.clienteTipoTarjeta,
                            numTarjeta: $scope.clienteNumeroTarjeta
                        }).then(function (response) {
//                            if (idsComentario.length > 0) {
//                                $http.put(clientesContext + "/" + response.data.id + "/comentarios", $scope.allComentariosCliente).then(function (response) {
//                                });
//                            }
                            //Author created successfully
                            $state.go('clientesList', {clienteId: response.data.id}, {reload: true});
                        });
                    };

                    $scope.newComentarios = function () {
                        $scope.allComentariosCliente = [];
                        for (var ite in idsComentario) {
                            for (var all in $scope.Allcomentarios) {
                                if ($scope.Allcomentarios[all].id === parseInt(idsComentario[ite])) {
                                    $scope.allComentariosCliente.push($scope.Allcomentarios[all]);
                                }
                            }
                        }
                    };
                }
            ]);
        }
)(window.angular);