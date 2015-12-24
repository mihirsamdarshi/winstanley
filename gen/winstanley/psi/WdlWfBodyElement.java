// This is a generated file. Not intended for manual editing.
package winstanley.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface WdlWfBodyElement extends PsiElement {

  @Nullable
  WdlCallBlock getCallBlock();

  @Nullable
  WdlDeclaration getDeclaration();

  @Nullable
  WdlIfStmt getIfStmt();

  @Nullable
  WdlScatterBlock getScatterBlock();

  @Nullable
  WdlWfOutputs getWfOutputs();

  @Nullable
  WdlWhileLoop getWhileLoop();

}
