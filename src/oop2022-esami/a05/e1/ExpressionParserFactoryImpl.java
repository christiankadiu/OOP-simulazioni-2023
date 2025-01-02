package a05.e1;

public class ExpressionParserFactoryImpl implements ExpressionParserFactory {

    @Override
    public ExpressionParser oneSum() {
        return new ExpressionParser() {
            int current = 0;

            @Override
            public void acceptNumber(int n) {
                if (this.current % 2 != 0 || this.current > 2) {
                    throw new IllegalStateException();
                }
                this.current++;
            }

            @Override
            public void acceptSum() {
                if (this.current % 2 == 0 || this.current > 2) {
                    throw new IllegalStateException();
                }
                this.current++;
            }

            @Override
            public void acceptOpenParen() {
                throw new IllegalStateException();
            }

            @Override
            public void acceptCloseParen() {
                throw new IllegalStateException();
            }

            @Override
            public void close() {
                if (this.current % 2 == 0 || this.current == 0) {
                    throw new IllegalStateException();
                }
            }

        };
    }

    @Override
    public ExpressionParser zeroOrManySums() {
        return new ExpressionParser() {
            int current = 0;

            @Override
            public void acceptNumber(int n) {
                if (this.current % 2 != 0) {
                    throw new IllegalStateException();
                }
                this.current++;
            }

            @Override
            public void acceptSum() {
                if (this.current % 2 == 0) {
                    throw new IllegalStateException();
                }
                this.current++;
            }

            @Override
            public void acceptOpenParen() {
                throw new IllegalStateException();
            }

            @Override
            public void acceptCloseParen() {
                throw new IllegalStateException();
            }

            @Override
            public void close() {
                if (this.current % 2 == 0 || this.current == 0) {
                    throw new IllegalStateException();
                }
            }

        };
    }

    @Override
    public ExpressionParser oneLevelParens() {
        return new ExpressionParser() {
            int current = 0;
            boolean par = true;

            @Override
            public void acceptNumber(int n) {
                if (this.current % 2 != 0) {
                    throw new IllegalStateException();
                }
                this.current++;
            }

            @Override
            public void acceptSum() {
                if (this.current % 2 == 0) {
                    throw new IllegalStateException();
                }
                this.current++;
            }

            @Override
            public void acceptOpenParen() {
                if (!par) {
                    throw new IllegalStateException();
                }
                par = false;
            }

            @Override
            public void acceptCloseParen() {
                if (par || this.current % 2 == 0) {
                    throw new IllegalStateException();
                }
                par = true;
            }

            @Override
            public void close() {
                if (this.current == 0 || !this.par) {
                    throw new IllegalStateException();
                }
            }

        };
    }

    @Override
    public ExpressionParser manySumsWithBoxingParens() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'manySumsWithBoxingParens'");
    }

}
