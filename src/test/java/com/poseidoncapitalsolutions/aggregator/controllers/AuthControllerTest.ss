╔═ auth ═╗

╔═ getLogin ═╗
<!DOCTYPE html>
<html
    class="h-dvh"
    data-theme="light" xmlns="http://www.w3.org/1999/xhtml"
>
    <head>
        <meta charset="UTF-8" />
        <link
            href="/favicon/favicon.svg"
            rel="icon"
        />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        
        <title>PoseidonCapitalSolution — Aggregator</title>
        <!-- TODO: Use an actual package manager instead of downloaded CDN file -->
        <link
            href="/css/bootstrap.min.css"
            rel="stylesheet"
            type="text/css"
        />
        <script src="/js/bootstrap.min.js"></script>
    </head>
    <body id="root" class="flex-1 flex-col">
        <div
            class="container-fluid d-flex align-items-center justify-content-center"
        >
            <div
                class="p-5 my-5 border d-flex flex-column align-items-center justify-content-center"
            >
                <form action="/auth/log-in" method="post" role="form"><input type="hidden" name="_csrf" value=""/>
                    

                    <div class="row">
                        <div>
                            <div class="d-flex justify-content-center mb-3">
                                <label>PoseidonCapitalSolution — Aggregator</label>
                            </div>
                            <div class="mb-3">
                                <label
                                    class="input input-bordered flex items-center gap-2"
                                >
                                    <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        viewBox="0 0 16 16"
                                        fill="currentColor"
                                        class="h-4 w-4 opacity-70"
                                    >
                                        <path
                                            d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6ZM12.735 14c.618 0 1.093-.561.872-1.139a6.002 6.002 0 0 0-11.215 0c-.22.578.254 1.139.872 1.139h9.47Z"
                                        />
                                    </svg>
                                    <input
                                        name="username"
                                        type="text"
                                        placeholder="Username"
                                        required
                                        class="form-control"
                                    />
                                </label>
                            </div>

                            <div class="mt-3">
                                <label
                                    class="input input-bordered flex items-center gap-2"
                                >
                                    <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        viewBox="0 0 16 16"
                                        fill="currentColor"
                                        class="h-4 w-4 opacity-70"
                                    >
                                        <path
                                            clip-rule="evenodd"
                                            fill-rule="evenodd"
                                            d="M14 6a4 4 0 0 1-4.899 3.899l-1.955 1.955a.5.5 0 0 1-.353.146H5v1.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-2.293a.5.5 0 0 1 .146-.353l3.955-3.955A4 4 0 1 1 14 6Zm-4-2a.75.75 0 0 0 0 1.5.5.5 0 0 1 .5.5.75.75 0 0 0 1.5 0 2 2 0 0 0-2-2Z"
                                        />
                                    </svg>
                                    <input
                                        name="password"
                                        type="password"
                                        placeholder="password"
                                        required
                                        class="form-control"
                                    />
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="mt-5 d-flex justify-content-center">
                        <button class="btn btn-primary w-100" type="submit">
                            Sign in
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>

╔═ [end of file] ═╗
