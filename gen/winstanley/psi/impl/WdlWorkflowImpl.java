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

public class WdlWorkflowImpl extends ASTWrapperPsiElement implements WdlWorkflow {

  public WdlWorkflowImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof WdlVisitor) ((WdlVisitor)visitor).visitWorkflow(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<WdlWfBodyElement> getWfBodyElementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, WdlWfBodyElement.class);
  }

}
