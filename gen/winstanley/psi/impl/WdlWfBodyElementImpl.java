// This is a generated file. Not intended for manual editing.
package winstanley.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static winstanley.psi.WdlTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import winstanley.psi.*;

public class WdlWfBodyElementImpl extends ASTWrapperPsiElement implements WdlWfBodyElement {

  public WdlWfBodyElementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof WdlVisitor) ((WdlVisitor)visitor).visitWfBodyElement(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public WdlCallBlock getCallBlock() {
    return findChildByClass(WdlCallBlock.class);
  }

  @Override
  @Nullable
  public WdlDeclaration getDeclaration() {
    return findChildByClass(WdlDeclaration.class);
  }

  @Override
  @Nullable
  public WdlIfStmt getIfStmt() {
    return findChildByClass(WdlIfStmt.class);
  }

  @Override
  @Nullable
  public WdlScatterBlock getScatterBlock() {
    return findChildByClass(WdlScatterBlock.class);
  }

  @Override
  @Nullable
  public WdlWfOutputs getWfOutputs() {
    return findChildByClass(WdlWfOutputs.class);
  }

  @Override
  @Nullable
  public WdlWhileLoop getWhileLoop() {
    return findChildByClass(WdlWhileLoop.class);
  }

}
